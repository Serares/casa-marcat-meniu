package com.RaresProject12;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface IOperatiiFisier {
    public HashMap<Integer, Produs> citesteMeniuDinFisier(String numeFisier) throws IOException;
    public List<Comanda> citesteComenziDinFisier(String numeFisier) throws IOException;
    public void scrieObiecteInFisier(String numeFisier) throws IOException;
}
