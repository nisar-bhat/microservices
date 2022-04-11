package com.nisar.core.product.controller;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.nisar.api.core.product.Product;
import com.nisar.core.product.domain.ProductEntity;
 
 
public interface ProductMapper {

  @Mappings({
    @Mapping(target = "serviceAddress", ignore = true)
  })
 default Product entityToApi(ProductEntity entity) {
	   
		    if (entity == null)
		      return null; 
		    Product product = new Product();
		    product.setProductId(entity.getProductId());
		    product.setName(entity.getName());
		    product.setWeight(entity.getWeight());
		    return product;
		  
  }

  @Mappings({
    @Mapping(target = "id", ignore = true), @Mapping(target = "version", ignore = true)
  })
 default ProductEntity apiToEntity(Product api) {
	  if (api == null)
	      return null; 
	    ProductEntity productEntity = new ProductEntity();
	    productEntity.setProductId(api.getProductId());
	    productEntity.setName(api.getName());
	    productEntity.setWeight(api.getWeight());
	    return productEntity;
  }
}