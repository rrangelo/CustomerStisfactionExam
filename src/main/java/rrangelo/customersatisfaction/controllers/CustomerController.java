/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 */
package rrangelo.customersatisfaction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rrangelo.customersatisfaction.beans.requests.customers.CustomerCreateCustomerRequestBean;
import rrangelo.customersatisfaction.beans.requests.customers.CustomerFindCustomerRequestBean;
import rrangelo.customersatisfaction.beans.requests.customers.CustomerUpdateCustomerRequestBean;
import rrangelo.customersatisfaction.beans.responses.customers.CustomerFindCustomerResponseBean;
import rrangelo.customersatisfaction.exceptions.responses.CustomerResponseException;
import rrangelo.customersatisfaction.services.CustomerService;

/**
 *
 * @author Ramon Rangel Osorio <ramon.rangel@protonmail.com>
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public void create(@RequestBody CustomerCreateCustomerRequestBean customer) {
        try {
            service.create(customer);
        } catch (Exception e) {
            throw new CustomerResponseException(e.getMessage());
        }
    }

    @GetMapping
    public CustomerFindCustomerResponseBean find(@RequestParam(value = "email") String email) {
        try {
            return service.find(CustomerFindCustomerRequestBean.builder()
                    .email(email)
                    .build()
            );
        } catch (Exception e) {
            throw new CustomerResponseException(e.getMessage());
        }
    }

    @PutMapping
    public void update(@RequestBody CustomerUpdateCustomerRequestBean customer) {
        try {
            service.update(customer);
        } catch (Exception e) {
            throw new CustomerResponseException(e.getMessage());
        }
    }

}
