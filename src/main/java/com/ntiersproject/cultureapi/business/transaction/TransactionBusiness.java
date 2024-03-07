package com.ntiersproject.cultureapi.business.transaction;

import com.ntiersproject.cultureapi.model.dto.Film;
import com.ntiersproject.cultureapi.model.dto.Transaction;

import java.util.List;

public interface TransactionBusiness {
    List<Transaction> createTransactions(Long idUtilisateur, List<Film> films);
}
