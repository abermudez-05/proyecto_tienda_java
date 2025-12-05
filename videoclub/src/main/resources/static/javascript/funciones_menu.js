//funciones generales:


// EL ANTIGUO PASO 3 → AHORA PASO 4
function checkout_paso_4(json){
    let html = Mustache.render(plantilla_checkout_4, json)
    $("#contenedor").html(html)
    $("#boton_confirmar_pedido").click(function(){
        $.post("pedidosREST/paso4").done(function(res){
            if(res == "ok"){
                obtenerPeliculas()
            }
        })
    })
}//end checkout paso4

function checkout_paso_3(json){
    let html = Mustache.render(plantilla_checkout_3, json)
    $("#contenedor").html(html)
    $("#aceptar_paso_3").submit(function(e){
		e.preventDefault()
        let genero = $("#genero_favorito").find(":selected").val()
		if(genero == "0"){
		    alert("selecciona un tipo de genero")
		    return
		}
        let pelicula = $("#pelicula_favorita").val()
		$.post("pedidosREST/paso3", {
		    generoFavorito: genero,
		    peliculaFavorita: pelicula
		}).done(function(res){
		    checkout_paso_4(res) // aquí res es el objeto resumen
		})

    })
}//end checkout paso3


function checkout_paso_2(){
    $("#contenedor").html(plantilla_checkout_2)
    $("#aceptar_paso_2").submit(function(e){
		e.preventDefault()
        let tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val()
        if(tipo_tarjeta == "0"){
            alert("selecciona un tipo de tarjeta")
            return
        }
        let numero = $("#numero_tarjeta").val()
        let titular = $("#titular_tarjeta").val()
        $.post("pedidosREST/paso2",{
            tarjeta : tipo_tarjeta,
            numero : numero,
            titular : titular
        }).done(function(res){
            // Ahora llamamos al nuevo paso 3
            checkout_paso_3(res)
        })
    })
}//end checkout paso2


function checkout_paso_1(){
    let nombre = $("#campo_nombre").val()
    let direccion = $("#campo_direccion").val()
    let provincia = $("#campo_provincia").val()
    let postal = $("#campo_postal").val()
    let telefono = $("#campo_telefono").val()
    let observaciones = $("#campo_observaciones").val()

    $.post("pedidosREST/paso1", {
        nombre: nombre,
        direccion: direccion,
        provincia: provincia,
        postal: postal,
        telefono: telefono,
        observaciones: observaciones
    }).done(function(res){
        if(res == "ok"){
            checkout_paso_2()
        }
    })
}//end checkout paso1


function checkout_paso_0(){
    $("#contenedor").html(plantilla_checkout_1)
	$("#aceptar_paso_1").submit(
			function(e){
				e.preventDefault()
				checkout_paso_1()
			} 
		)
}//end checkout paso0

	
function mostrarCarrito(){
	if(nombre_login == ""){
		alert("Tienes que identificarte para comprar productos")
	}else{
		//$("#contenedor").html(plantilla_carrito)
		$.get("carritoREST/obtener", function(r){
			if(r.length == 0){
				alert("Aún no has agregado nada al carrito")
			}else{
				let html = Mustache.render(plantilla_carrito,r)
				$("#contenedor").html(html)
				$("#realizar_pedido").click(checkout_paso_0)
				$(".enlace-borrar-producto-carrito").click(function(){
					if(! confirm("Estas seguro")){
						return
					}
					let idPelicula = $(this).attr("id-pelicula")
					$.post("carritoREST/eliminar",{
						id: idPelicula
					}).done(function(res){
						alert(res)
					})
				})
			}
		})
	}
}	

function comprar_producto(){
	if(nombre_login == ""){
		alert("Tienes identificarte para comprar productos")
	}else{
		var id_producto = $(this).attr("id-producto")
		$.post("carritoREST/agregarProducto",{
			id: id_producto,
			cantidad: 1
		}).done(function(res){
			alert(res)
		})		
	}
	
}	

function obtenerPeliculas(){
    $("#contenedor").html("cargando...");

    $.get("peliculasREST/obtener", function(r){
        var peliculas = r;

        // Renderizamos carrusel
        var htmlCarrusel = Mustache.render(plantilla_carrusel, { array_peliculas: peliculas });

        // Renderizamos listado clásico
        var htmlListado = Mustache.render(plantilla_peliculas, { array_peliculas: peliculas });

        // Insertamos ambos en el contenedor: primero carrusel, luego listado
        $("#contenedor").html(htmlCarrusel + htmlListado);

        // Activamos eventos
        $(".enlace_comprar_producto").click(comprar_producto);
        inicializarCarrusel();
    });
}


function inicializarCarrusel(){
    const track = document.querySelector(".carrusel-track");
    const items = document.querySelectorAll(".carrusel-item");
    const btnIzq = document.querySelector(".carrusel-boton.izq");
    const btnDer = document.querySelector(".carrusel-boton.der");

    let index = 0;
    const total = items.length;

    function mostrarItem(i){
        track.style.transform = `translateX(-${i * 100}%)`;
    }

    btnIzq.addEventListener("click", () => {
        index = (index > 0) ? index - 1 : total - 1;
        mostrarItem(index);
    });

    btnDer.addEventListener("click", () => {
        index = (index < total - 1) ? index + 1 : 0;
        mostrarItem(index);
    });
}




function mostrarLogin(){
	$("#contenedor").html(plantilla_login)
	$("#form_login").submit(function(e){
		e.preventDefault()
		var email = $("#email").val()
		var pass = $("#pass").val()
		$.post("usuariosREST/login",{
			email: email, 
			pass: pass
		}).done(function(res){
			var parte1 = res.split(",")[0]
			var parte2 = res.split(",")[1]
			if(parte1 == "ok"){
				alert("Bienvenido " + parte2 + ". Ya pudes comprar")
				nombre_login = parte2
				$("#login_usuario").html("hola " + parte2)
			}else{
				alert(res)
			}	
		})//end done
	})
}//end matrarLogin

function mostrarRegistro(){
	$("#contenedor").html(plantilla_registro)
	//vamos a interceptar el envio de formulario
	$("#form_registro").submit(function(e){
		e.preventDefault()
		//alert("se intenta enviar form")
		//recoger los datos del form y mandarselos a UsuariosREST
		var nombre = $("#nombre").val()
		var email = $("#email").val()
		var pass = $("#pass").val()
		var telefono = $("#telefono").val()
		var nickname = $("#nickname").val()
		$.post("usuariosREST/registrar",{
			nombre: nombre, 
			email: email, 
			pass: pass,
			telefono: telefono,
			nickname: nickname
		}).done(function(res){
			alert(res)
		})//end done
	})//end submit form
}//end mostrarRegistro

//atencion a eventos
$("#enlace_productos").click(obtenerPeliculas)
$("#enlace_identificarme").click(mostrarLogin)
$("#enlace_registrarme").click(mostrarRegistro)
$("#enlace_carrito").click(mostrarCarrito)