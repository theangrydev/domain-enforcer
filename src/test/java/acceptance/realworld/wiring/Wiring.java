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
package acceptance.realworld.wiring;

import acceptance.realworld.application.FooRequestRepository;
import acceptance.realworld.infrastructure.httpclient.HttpClientFactory;
import acceptance.realworld.userinterface.httpserver.HttpServerBuilder;
import acceptance.realworld.domain.portingauthorization.FooService;
import acceptance.realworld.infrastructure.httpclient.HttpClientFactoryImplementation;
import acceptance.realworld.infrastructure.httpserver.HttpServerBuilderImplementation;
import acceptance.realworld.infrastructure.persistence.PretendFooRequestRepository;
import acceptance.realworld.infrastructure.thirdparty.ThirdPartyClient;

public class Wiring {

    public FooRequestRepository repository() {
        return new PretendFooRequestRepository();
    }

    public HttpServerBuilder httpServerBuilder() {
        return new HttpServerBuilderImplementation();
    }

    public FooService portingAuthorizationService() {
        return new ThirdPartyClient(httpClientFactory());
    }

    private HttpClientFactory httpClientFactory() {
        return new HttpClientFactoryImplementation();
    }
}
