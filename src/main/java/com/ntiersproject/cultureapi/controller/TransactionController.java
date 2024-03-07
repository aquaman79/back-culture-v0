package com.ntiersproject.cultureapi.controller;

import com.ntiersproject.cultureapi.business.panier.PanierBusiness;
import com.ntiersproject.cultureapi.business.transaction.TransactionBusiness;
import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Panier;
import com.ntiersproject.cultureapi.model.dto.Transaction;
import com.ntiersproject.cultureapi.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/transactions")
public class TransactionController {

    private TransactionBusiness transactionBusiness;

    private JwtTokenProvider jwtTokenProvider;

    @Context
    private HttpServletRequest httpServletRequest;

    public TransactionController(TransactionBusiness transactionBusiness, JwtTokenProvider jwtTokenProvider) {
        this.transactionBusiness = transactionBusiness;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @POST
    @Path("/{idUtilisateur}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransactions(@PathParam("idUtilisateur") Long idUtilisateur, List<Film> films) {
        List<Transaction> transactions = transactionBusiness.createTransactions(idUtilisateur, films);
        return Response.status(201).entity(transactions).build();
    }
}
