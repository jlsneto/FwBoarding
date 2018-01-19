package login;

import model.vo.UsuarioVO;

public class UsuarioSessao {
	
	private static UsuarioVO usuarioAtivo;

	public static UsuarioVO getUsuarioAtivo() {
		return usuarioAtivo;
	}

	public static void setUsuarioAtivo(UsuarioVO usuarioAtivo) {
		UsuarioSessao.usuarioAtivo = usuarioAtivo;
	}
	
}
