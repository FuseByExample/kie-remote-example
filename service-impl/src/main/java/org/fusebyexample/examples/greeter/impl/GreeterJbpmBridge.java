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
package org.fusebyexample.examples.greeter.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.fusebyexample.examples.greeter.Greeter;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;

public class GreeterJbpmBridge implements Greeter {

  private final RuntimeEngine runtimeEngine;
  private final String processId;

  public GreeterJbpmBridge(RuntimeEngine runtimeEngine, String processId) {
    this.runtimeEngine = Objects.requireNonNull(runtimeEngine);
    this.processId = Objects.requireNonNull(processId);
  }

  public RuntimeEngine getRuntimeEngine() {
    return runtimeEngine;
  }

  public String getProcessId() {
    return processId;
  }

  @Override
  public String requestGreeting(String callbackId, String callbackUri, String name) {
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("callbackId", callbackId);
    parameters.put("callbackUri", callbackUri);
    parameters.put("name", name);
    
    KieSession kieSession = runtimeEngine.getKieSession();
    ProcessInstance processInstance = kieSession.startProcess(processId, parameters);
    return Long.toString(processInstance.getId());
  }
}
