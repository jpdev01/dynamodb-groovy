package com.jpdev.main.service

import com.jpdev.main.domain.Product

interface ProductService {

    public List<Product> list()

    public Product get(Long id)

    public Product save(Map info)

}
