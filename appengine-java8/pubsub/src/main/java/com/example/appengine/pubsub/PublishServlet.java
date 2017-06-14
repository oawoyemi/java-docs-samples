/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.appengine.pubsub;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@WebServlet(value = "/publish")
public class PublishServlet extends HttpServlet {

  private final ExecutorService executor =
      new ThreadPoolExecutor(1, 1, 0L,
          TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
  private volatile PublishHelper publishHelper;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    synchronized (executor) {
      try {
        String topicId = System.getenv("PUBSUB_TOPIC");
        String payload = req.getParameter("payload");
        publishHelper = new PublishHelper(topicId, payload);
        publishHelper.publish();
        //executor.execute(publishHelper);
      } catch (Exception e) {
        resp.sendError(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
      }
    }
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter out = resp.getWriter();
    synchronized (executor) {
      if (publishHelper != null) {
        resp.setContentType("text");
        resp.getWriter().append(publishHelper.getOutput());
     }
    }
    out.close();
  }


  @Override
  public void destroy() {
    executor.shutdownNow();
    super.destroy();
  }
}
