/*
 * Copyright 2013- Yan Bonnel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ybonnel.breizhcampdemo;

import fr.ybonnel.simpleweb4j.exception.HttpErrorException;
import fr.ybonnel.simpleweb4j.handlers.Route;
import fr.ybonnel.simpleweb4j.handlers.RouteParameters;
import fr.ybonnel.simpleweb4j.handlers.filter.AbstractFilter;

import javax.servlet.http.HttpServletResponse;

public class SecurityFilter extends AbstractFilter {
    @Override
    public void handle(Route route, RouteParameters routeParams) throws HttpErrorException {
        if (!"admin".equals(routeParams.getParam("login"))
                || !"admin".equals(routeParams.getParam("password"))) {
            throw new HttpErrorException(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
