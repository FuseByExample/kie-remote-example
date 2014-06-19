/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusebyexample.examples.wih;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.fusebyexample.examples.greetercb.GreeterCallbackService;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreeterCallbackWih implements WorkItemHandler {

  private static final Logger logger = LoggerFactory.getLogger(GreeterCallbackWih.class);

  @Override
  public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
    String callbackId = (String) workItem.getParameter("callbackId");
    String callbackUri = (String) workItem.getParameter("callbackUri");
    String greeting = (String) workItem.getParameter("greeting");

    URL greetingCbServiceUri = null;
    try {
      greetingCbServiceUri = URI.create(callbackUri + "?wsdl").toURL();
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }

    GreeterCallbackService gService = new GreeterCallbackService(greetingCbServiceUri);
    gService.getGreeterCallbackPort().requestGreetingCallback(callbackId, greeting);
    Map result = new HashMap();
    manager.completeWorkItem(workItem.getId(), result);
  }

  @Override
  public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
    // Do nothing, cannot be aborted
  }
}
