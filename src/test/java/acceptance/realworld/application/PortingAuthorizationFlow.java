/*
 * Copyright 2016 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of domain-enforcer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package acceptance.realworld.application;

import acceptance.realworld.domain.Widget;
import acceptance.realworld.domain.portingauthorization.FooResult;
import acceptance.realworld.domain.portingauthorization.FooService;

@SuppressWarnings("unused")
public class PortingAuthorizationFlow {

    private final FooService fooService;
    private final FooRequestRepository fooRequestRepository;

    public PortingAuthorizationFlow(FooService fooService, FooRequestRepository fooRequestRepository) {
        this.fooService = fooService;
        this.fooRequestRepository = fooRequestRepository;
    }

    public FooResult requestPortingAuthorizationCode(Widget widget) {
        FooResult fooResult = fooService.requestPortingAuthorizationCode(widget);
        fooRequestRepository.savePacRequest(widget, fooResult);
        return fooResult;
    }
}
