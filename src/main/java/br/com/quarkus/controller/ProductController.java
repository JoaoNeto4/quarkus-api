package br.com.quarkus.controller;

import java.util.List;

import br.com.quarkus.dto.ProductDTO;
import br.com.quarkus.entity.Product;
import br.com.quarkus.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {


    @Inject
    ProductService service;

    @GET
    public Response listProducts(){
        
        List<Product> products = service.listProduct();
        
        return Response.ok(products).build();
    }

    @POST
    public Response saveProduct(ProductDTO dto){
        
        Product product = service.saveProduct(dto);

        return Response.ok(product).status(201).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProduct(@PathParam("id")Long id, ProductDTO dto){
        
        service.updateProduct(id, dto);

        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeProduct(@PathParam("id") Long id){
        
        service.removeProduct(id);

        return Response.status(204).build();
    }
    
}
