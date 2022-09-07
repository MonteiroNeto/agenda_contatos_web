/**
 * Confirmação de exclusão de um contato
 * @author Monteiro neto
 */
 
 function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão do contato ?")
	
	if(resposta === true){
		//alert(idcon)
		
		//encaminhar o id para o SERVLET 
		window.location.href = "delete?idcon="+idcon
	
	}
}