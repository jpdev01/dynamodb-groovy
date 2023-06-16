package com.jpdev.main.controller

import com.jpdev.main.domain.Product
import com.jpdev.main.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/Products')
class ProductController {

    @Autowired
    private final ProductService productService

    @RequestMapping(method = RequestMethod.GET)
    List<Product> list() {
        return productService.list()
    }

    @RequestMapping(value = '/{id}', method = RequestMethod.GET)
    Product getProduct(@PathVariable('id') int id) {
        return productService.get(id)
    }

    @RequestMapping(method=RequestMethod.POST)
    public Product create(@RequestBody Map ProductInfo) {
        return productService.save(ProductInfo)
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "O formato da requisição é inválida.")
    public void onIllegalArgumentException(IllegalArgumentException exception) {
        println "APP -> Logando erro ${new Date()}: ${exception.message}."
    }
}
