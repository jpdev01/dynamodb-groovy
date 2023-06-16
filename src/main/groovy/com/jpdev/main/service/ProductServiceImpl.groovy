package com.jpdev.main.service

import com.jpdev.main.domain.Product
import com.jpdev.main.repository.ProductRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository

    public List<Product> list() {
        return repository.findAll()
    }

    public Product get(Long id) {
        Optional<Product> holder = repository.findById(id)
        if (holder.isPresent()) return holder.get()
        return null
    }

    public Product save(Map info) {
        Product product = new Product()
        product.setCost(info.cost?.toString())
        return product
    }
}