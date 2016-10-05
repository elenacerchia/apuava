/*
 * Copyright (c) 2016 WisePersist.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wisepersist.apuava.urlfetch;

import com.google.inject.Singleton;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is for testing {@link UrlFetcher} with POST method.
 *
 * @author jiakuan.wang@gmail.com
 */
@Singleton
public class TestPostServlet extends HttpServlet {

  @Override
  protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {
    final Enumeration<String> parameterNames = req.getParameterNames();
    final UrlFetcher urlFetcher = new UrlFetcher();
    while (parameterNames.hasMoreElements()) {
      final String name = parameterNames.nextElement();
      final String value = req.getParameter(name);
      urlFetcher.addParam(name, value);
    }
    resp.getWriter().print(urlFetcher.getQueryString());
  }
}
