package login;

import java.sql.SQLException;

public class GestorResultados {

    private final ResultadoBalanceodao balanceodao = new ResultadoBalanceodao();
    private final ResultadoNomenclaturadao nomenclaturadao = new ResultadoNomenclaturadao();

    public void registrarResultado(Resultado resultado) {
        balanceodao.insertar(resultado);
    }

    public Double desempenoBalanceo(String estudiante) throws SQLException {
        return balanceodao.calcularDesempeno(estudiante);
    }

    public Double desempenoNomenclatura(String estudiante) throws SQLException {
        return nomenclaturadao.calcularDesempeno(estudiante);
    }
}