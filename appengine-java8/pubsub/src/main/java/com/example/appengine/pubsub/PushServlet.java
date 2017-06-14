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
/*package com.example.appengine.pubsub;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@WebServlet(value = "/push")
public class PushServlet extends HttpServlet {

  private final ByteArrayOutputStream resultBytes;
  private final PrintStream resultStream;

  public PushServlet() {
    this.resultBytes = new ByteArrayOutputStream();
    this.resultStream = new PrintStream(this.resultBytes);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String pubsubVerificationToken = System.getenv("PUBSUB_VERIFICATION_TOKEN");
    // Do not process message if request token does not match pubsubVerificationToken
    if (req.getParameter("token").compareTo(pubsubVerificationToken) != 0) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    // parse message object from "message" field in the request body json
    // decode message data from base64
    Message message = getMessage(req);
    try {
      resultStream.append(message.getMessageId());
      resultStream.append("\n");
      resultStream.append(message.getData());
      // 200, 201, 204, 102 status codes are interpreted as success by the Pub/Sub system
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("text");
    resp.getWriter().append(getOutput());
    out.close();
  }

  String getOutput() {
    //works because PrintStream is thread safe synchronizing on "this".
    synchronized (resultStream) {
      try {
        return resultBytes.toString("UTF-8");
      } catch (UnsupportedEncodingException e) {
        return null;
      }
    }
  }

  private Message getMessage(HttpServletRequest request) throws IOException {
    String requestBody = request.getReader().lines().collect(Collectors.joining("\n"));
    JsonElement jsonRoot = jsonParser.parse(requestBody);
    String messageStr = jsonRoot.getAsJsonObject().get("message").toString();
    Message message = gson.fromJson(messageStr, Message.class);
    // decode from base64
    String decoded = decode(message.getData());
    message.setData(decoded);
    return message;
  }

  private String decode(String data) {
    return new String(Base64.getDecoder().decode(data));
  }

  private final Gson gson = new Gson();
  private final JsonParser jsonParser = new JsonParser();

  @Override
  public void destroy() {
    super.destroy();
  }
}
*/
