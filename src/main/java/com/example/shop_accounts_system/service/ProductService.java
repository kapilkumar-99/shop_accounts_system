package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddProductRequest;
import com.example.shop_accounts_system.dto.GetProductResponse;
import com.example.shop_accounts_system.dto.ProductDTO;
import com.example.shop_accounts_system.dto.UpdateProductRequest;
import com.example.shop_accounts_system.entity.Product;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.repository.ProductRepository;
import com.example.shop_accounts_system.repository.PurchaseRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    public ProductDTO addProduct(AddProductRequest addProductRequest){
        Product product = Product.toEntity(addProductRequest);
        Product newProduct = productRepository.save(product);
        return ProductDTO.fromEntity(newProduct);
    }

    public ProductDTO updateProduct(String productId, UpdateProductRequest request) throws Exception {
        var existingProduct = productRepository.findById(Integer.parseInt(productId))
                                                .orElseThrow(()->new NotFoundException("Invalid product id"+productId));
        if(request.getName() != null) {
            existingProduct.setName((request.getName()));
        }
        if(request.getQuantity() > 0) {
            existingProduct.setQuantity(request.getQuantity());
        }
        if(request.getCurrentPrice() > 0) {
            existingProduct.setCurrentPrice(request.getCurrentPrice());
        }

        Product updatedProduct = productRepository.save(existingProduct);

        return ProductDTO.fromEntity(updatedProduct);
    }

    public GetProductResponse getProductById(String id) throws Exception{
        Product product = productRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Product was not found with id "+id));
       GetProductResponse getProduct = new GetProductResponse(product.getId(), product.getName(),product.getCurrentPrice(),product.getQuantity());
       return getProduct;
    }

    public List<GetProductResponse> findAllProduct(){
        List<Product> products = (List<Product>) productRepository.findAll();
        List<GetProductResponse> allProducts = new ArrayList<>();
        for(Product product: products){
            GetProductResponse getProductResponse = new GetProductResponse(product.getId(), product.getName(),product.getCurrentPrice(),product.getQuantity());
            allProducts.add(getProductResponse);
        }
        return allProducts;
    }

    public void ProductDeleteById(String id) throws Exception{
        Product product = productRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Product was not found with id "+id));
        productRepository.deleteById(Integer.parseInt(id));
        throw new Exception("Product was sucessfully delete with id "+id);
        // return "deleted sucessfully with id "+id;
    }
}
