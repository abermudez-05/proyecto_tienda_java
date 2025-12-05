
$.get("plantillas_mustache"+idioma+"/registrarme.html", function(r){
	plantilla_registro = r
})

$.get("plantillas_mustache"+idioma+"/login.html", function(r){
	//codigo que se ejecuta cuando el navegdor ha descargado en r el contendido del archivo html indicado
	plantilla_login = r
})

$.get("plantillas_mustache"+idioma+"/peliculas.html", function(r){
	plantilla_peliculas = r
})

$.get("plantillas_mustache"+idioma+"/carrito.html", function(r){
	plantilla_carrito = r
})

$.get("plantillas_mustache"+idioma+"/checkout_1.html", function(r){
	plantilla_checkout_1 = r
})

$.get("plantillas_mustache"+idioma+"/checkout_2.html", function(r){
	plantilla_checkout_2 = r
})

$.get("plantillas_mustache"+idioma+"/checkout_3.html", function(r){
	plantilla_checkout_3 = r
})

$.get("plantillas_mustache"+idioma+"/checkout_4.html", function(r){
	plantilla_checkout_4 = r
})

$.get("plantillas_mustache"+idioma+"/carrusel.html", function(r){
	plantilla_carrusel = r
})
