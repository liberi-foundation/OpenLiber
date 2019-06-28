function validaForm(){
var formuser= document.getElementById("formuser");
 var nome= document.getElementById("nome").value;
 var email= document.getElementById("email").value;
 var senha= document.getElementById("senha").value;
 var rep_senha= document.getElementById("rep_senha").value;

 if(nome == "" || nome == null || nome.lenght < 3){

alert("preencha o campo nome");
nome.focus();
return false;
}

if(email == "" || email.indexOf("@") == -1 || email.indexOf(".") == -1 || email == null){

alert("preencha o campo email");
email.focus();
return false;
}

if(senha == "" || senha.length <= 7 || senha == null){

alert("preencha o campo nome com mínimo 8 caracteres");
senha.focus();
return false;
}
if(rep_senha == "" || rep_senha.length <= 7 || rep_senha == null){
alert("repita a senha no campo abaixo");
rep_senha.focus();
}
if(senha != rep_senha){
alert("Senhas diferentes");
rep_senha.focus();
return false;
}
}