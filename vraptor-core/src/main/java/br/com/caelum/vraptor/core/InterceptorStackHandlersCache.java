package br.com.caelum.vraptor.core;

/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.LinkedList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.interceptor.InterceptorRegistry;

@ApplicationScoped
public class InterceptorStackHandlersCache {
	
	private InterceptorRegistry registry;
	private InterceptorHandlerFactory handlerFactory;
	
	private final LinkedList<InterceptorHandler> interceptorHandlers = new LinkedList<>();
	
	@Deprecated //CDI eyes only
	public InterceptorStackHandlersCache() {}

	@Inject
	public InterceptorStackHandlersCache(InterceptorRegistry registry,InterceptorHandlerFactory handlerFactory){
		this.registry = registry;
		this.handlerFactory = handlerFactory;

		for (Class<?> interceptor : registry.all()) {
			this.interceptorHandlers.addLast(handlerFactory.handlerFor(interceptor));
		}
	}
	
	public LinkedList<InterceptorHandler> getInterceptorHandlers() {
		return new LinkedList<>(interceptorHandlers);
	}

}