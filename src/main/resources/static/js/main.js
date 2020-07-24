$(document).ready(function() {
	  $(document).on('keyup', '.montoPago, .cantidad, .cantidadPedido' ,function() {
	        this.value = this.value.replace(/[^0-9]/g, '');
	  });
	  
	$('.rutCliente').Rut({
		  on_error: function(){ 
		  	alert('Rut incorrecto'); 
		  $(".btn-enviarPedido").attr("disabled");	
		  },
		  on_success: function(){ 
		    alert('Rut correcto'); 
		    $(".btn-buscar-persona").removeAttr("disabled");
		  },
		  format_on: 'keyup'
	});
	
	$('.rut-buscar').Rut({
		  on_error: function(){ 
		  	alert('Rut incorrecto'); 
		  },
		  format_on: 'keyup'
	});

	//Obtiene tipo de producto según el tipo de familia
	$(".selectFamilia").change(function() {
	
		$(".selectProducto").empty();
		var option = '<option value ="">Seleccione producto</option>';
		$(".selectProducto").append(option);
		
		var idFamilia = $(".selectFamilia").val();
		$.ajax({
			url : '/request/tipos-productos',
			data : {"idFamilia" : idFamilia},
			method : "POST",
			dataType : "json",
			success : function(lista) {
				$(".selectTipoProducto").empty();
				var option = '<option value ="">Seleccione tipo producto</option>';
				$(".selectTipoProducto").append(option);
				$.each( lista, function( key, objeto ) {
					var option = '<option value ="'+objeto.id+'">'+objeto.nombre+'</option>';
					$(".selectTipoProducto").append(option);
				});
			},
			error : function() {
				console.log("No se ha podido obtener la información");
			}
		});
	});
	
	//Obtiene los productos según la marca o el tipo o ambos.
	$(".selectTipoProducto, .selectMarca").change(function() {
		var idTipo = $(".selectTipoProducto").val();
		var idMarca = $(".selectMarca").val();
		$.ajax({
			url : '/request/productos',
			data : {"idTipo" : idTipo, "idMarca" : idMarca},
			method : "POST",
			dataType : "json",
			success : function(lista) {
				$(".selectProducto").empty();
				var option = '<option value ="">Seleccione producto</option>';
				$(".selectProducto").append(option); //Agrega el primer termino y luego recorre la lista
				$.each( lista, function( key, objeto ) {
					var option = '<option value ="'+objeto.id+'">'+objeto.nombre+'</option>';
					$(".selectProducto").append(option);
				});
			},
			error : function() {
				console.log("No se ha podido obtener la información");
			}
		});
	});
	
	$(".btn-agregar").click(function() {
		var idProducto = $(".selectProducto").val();
		var htmlDetalle = "";
		var existe = false;
		
		$( ".detalles input[name='idProducto[]']" ).each(function( index ) {
			  var idProductoComparativo = $( this ).val();
			  
			  if(idProducto == idProductoComparativo){
				  existe = true;
			  }
		});
		
		if(existe){
			alert("El producto ya existe en la lista");
			return false;
		}
		
		if(idProducto != ""){
			
			if($( ".detalles input[name='idProducto[]']" ).length == 0) {
				$( ".detalles" ).empty()
				$(".btn-enviarPedido").removeAttr("disabled");
			}
		
			var nombreProducto = $(".selectProducto  option:selected").text();
			htmlDetalle += '<div class="row">'
						+ '<div class="form-group col-md-4">' 
					    + '<input name="idProducto[]" type="hidden" value="'+idProducto+'">' 
					    + '<input value="X"  type="button" class="btn btn-danger mr-2 eliminarProductoPedido"/>'
					    + '<label>'+nombreProducto+'</label>'
						+ '</div>'
						+ '<div class="form-group col-md-4">'
						+ '<label>Ingrese cantidad</label>'
						+ '<input required name="cantidad[]" type="number" min="1" class="cantidadPedido form-control">'
						+ '</div>'
						+ '<div class="form-group col-md-4">'
						+ '<label>Precio unitario</label>'
						+ '<input required name="precioUnitario[]" type="number" min="1" max="9999999" class="form-control">'
						+ '</div>'
						+ '</div>';
			$(".detalles").append(htmlDetalle);
			
			
			
		}
	});	
	
	$(document).on('keyup', '.cantidad' ,function() {
		console.log("entro de nuevo");
		var cantidad = $( this ).val();
		var precio = $( this ).parent().parent().find(".precios").val();
		var total = cantidad * precio;
		$( this ).parent().parent().find(".total").attr('value',total);
		
		console.log(this);
		console.log(cantidad);
		console.log(precio);
		console.log(total);
		
		var sumatoria = 0;
		$( ".total" ).each(function( index, element ) {
			console.log($(element).val());
			sumatoria += parseInt($(element).val());
			
		});
		
		$(".totalFinal").val(sumatoria);
		$(".montoPagar").val(sumatoria);
		
	});
	
	$(".btn-buscar").click(function() {
		console.log('entro');
		var codigoBarra = $(".ingresarCodigo").val();
		var htmlDetalle = "";
		var existe = false;
		
		if(codigoBarra == ""){
			$(".ingresarCodigo").focus();
			return false;
		}

		
		$( ".productos .codigos" ).each(function( index ) {
			  var idCodigoComparativo = $( this ).val();
			  
			  if(codigoBarra == idCodigoComparativo){
				  existe = true;
			  }
			});
		
		if(existe){
			alert("El producto ya existe en la lista");
			return false; 
		} 
		
		$.ajax({
			url : '/request/venta-productos',
			data : {"codigoBarra" : codigoBarra},
			method : "POST",
			dataType : "json",
			success : function(objeto) {
				console.log(objeto);
				
				if(objeto.validador == false){
					alert("El producto no existe");
					return false;
				}
				
				
				if($( ".productos input[name='productos[]']" ).length == 0) {
					$( ".productos" ).empty()
					$("#generarBoleta").removeAttr("disabled");
				}
				
				$(".ingresarCodigo").empty(); 
				
				var html = '';
				html += '<div class="row mt-2">';
				html += '<div class="col-md-3">';
				html += '<input value="X"  type="button" class="btn btn-danger mr-2 eliminarProducto"/>';
				html += '<label>'+objeto.nombre+'</label>';
				html += '</div>';
				html += '<input class="form-control" name="productos[]" value="'+objeto.id+'" type="hidden"/>';// input para recibir los valores del producto
				html += '<input class="codigos" value="'+codigoBarra+'" type="hidden"/>';// input para recibir el codigo de barra
				html += '<div class="col-md-3">';
				html += '<input class="form-control cantidad" type="number" name="cantidad[]" required placeholder ="cantidad"/>'; // input para recibir los valores escribiendo
				html += '</div>';
				html += '<div class="col-md-3">';
				html += '<input class="form-control precios" type="text" value="'+objeto.precio+'" disabled/>'; // input para recibir los precios del producto
				html += '</div>';
				html += '<div class="col-md-3">';
				html += '<input class="form-control total" type="text" value="0" disabled/>';
				html += '</div>';
				html += '</div>';
				
				
				$(".productos").append(html);
					
						
			},
			error : function() {
				console.log("No se ha podido obtener la información");
			}
	});	

});
	
	$(".btn-buscar-persona").click(function(e) {
		e.preventDefault();
		var rutCliente = $(".rutCliente").val();
		var html = '';
		
		if(rutCliente == ""){
			$(".rutCliente").focus();
			return false;
		}
		
		$.ajax({
			url : '/request/buscar-rut',
			data : {"rutCliente" : rutCliente},
			method : "POST",
			dataType : "json",
			success : function(objeto) {
				console.log(objeto);
				
				if(objeto.validador == false){ 
					//Aquí acepta
					if (confirm('La persona no existe, ¿Desea agregarla?')) {
						$(".registroPersona").show();
						$("input[name=rut]").focus();
						$("input[name=rut]").val(rutCliente); 
					} 
					return false;
				}		
				
				$("input[name=idCliente]").val(objeto.id);
				alert('rut ' +objeto.rut+ ' agregado exitosamente');

				 html = 'nombre :' + objeto.nombre + '<br>';
				 html += 'rut :' + objeto.rut + '<br>';
				 html += 'mail :' + objeto.mail + '<br>';
				
				$(".infoPersona").html(html);
			},
			error : function() {
				console.log("No se ha podido obtener la información");
			}
	});	

});
	$(".btn-guardar-persona").click(function(e) {
		e.preventDefault();
		var rut = $(".rut").val();
		var mail = $(".mail").val();
		var nombre = $(".nombreCliente").val();
		html = '';
		
		if(rut == ""){
			$(".rut").focus();
			return false;
		}
		
		$.ajax({
			url : '/request/guardar-persona',
			data : {"rut" : rut, "mail" : mail, "nombre" : nombre},
			method : "POST",
			dataType : "json",
			success : function(objeto) {
				console.log(objeto);
				
				if(objeto.existe){
					alert("rut ya existe");
					return false;
				}
				
				$(".registroPersona").hide();
				$("input[name=idCliente]").val(objeto.id);
				alert("Cliente " +nombre+ " creado exitosamente");
				
				html += '<div>';
				html += 'Rut: ' + rut;
				html += '</div>';
				html += '<div>';
				html += 'Nombre: ' + nombre;
				html += '</div>';
				html += '<div>';
				html += 'Mail: ' + mail;
				html += '</div">';
				
				$(".infoPersona").html(html);
				
				
			},
			error : function() {
				console.log("No se ha podido obtener la información");
			}
	});	
});

	$("select[name=idMedioPago]").change(function() {
		var idMedio = $("select[name=idMedioPago]").val();
		var montoPago = $(".totalFinal").val();
		
		var VAR_ID_DEUDA = parseInt($("#VAR_ID_DEUDA").val());
		var VAR_ID_EFECTIVO = parseInt($("#VAR_ID_EFECTIVO").val());
		
		if(idMedio == VAR_ID_EFECTIVO || idMedio == VAR_ID_DEUDA){ // si el medio de pago es efectivo o deuda
			$(".pagoEfectivo").show();
			$(".montoPago").focus();
			$(".montoPago").attr("required", "required");
		} else {
			$(".pagoEfectivo").hide();
			$(".montoPago").removeAttr("required");

		}
		
		$(".montoPago").val(0);	
		$(".vuelto").val(0);
	});	
	
	$(".montoPago").change(function() {
		var montoPago = parseInt($( this ).val());
		var pagar = parseInt($(".montoPagar").val());
		var idMedio = $ ("select[name=idMedioPago]" ).val();
		var vuelto = 0;
		
		var VAR_ID_EFECTIVO = parseInt($("#VAR_ID_EFECTIVO").val());
		
		if(pagar == 0){
			alert("Ingrese un producto");
			return false;
		}
		
		if(idMedio == VAR_ID_EFECTIVO){
			if(montoPago < pagar){
				alert("El monto pago debe ser mayor al monto a pagar");
				$(".vuelto").val(0);
				return false;
			}
			
			vuelto = montoPago - pagar;
			$(".vuelto").val(vuelto);
		}
	});
	
	$("#generarBoleta").click(function(e) {
		e.preventDefault();
		
		var VAR_ID_DEUDA = parseInt($("#VAR_ID_DEUDA").val());
		var VAR_ID_EFECTIVO = parseInt($("#VAR_ID_EFECTIVO").val());
		
		var rut = $( "input[name=idCliente]" ).val();
		var idMedio = $( "select[name=idMedioPago]" ).val();
		var validarProductos = $(".totalFinal").val();
		var montoPago = parseInt($( ".montoPago" ).val());
		var montoPagar = parseInt($( ".montoPagar" ).val());
		
		if(validarProductos <= 0){
			alert('Debe ingresar un producto');
			return false;
		}
		
		var cantidadNoValida = false;
		$( ".productos input[name='cantidad[]']" ).each(function( index, element ) {
			if ($(element).val() <= 0){
				cantidadNoValida = true;
			} 
			
		});
		
		if (cantidadNoValida) {
			alert('Cantidad de algun producto no es valida');
			return false;
		}
		
		if(idMedio == 0){
			alert('Debe seleccionar medio de pago');
			return false;
		}
	
		if(idMedio == VAR_ID_EFECTIVO){
			if(montoPago < montoPagar){
				alert('el monto de pago debe ser mayor al valor de la compra');
				
				return false;
			}
		}
		
		if(idMedio == VAR_ID_DEUDA && rut == 0){
			alert('debe registrar o asociar un rut a la compra');
			return false;
		}

		$("#form-boleta").submit();	
	});	
	
	$("#abonar").click(function(e) {
		e.preventDefault();
			var montoDeuda = $( "input[name=montoDeuda]" ).val();
			var montoAbonos = $( "input[name=montoAbonos]" ).val();
			var abono = $( "input[name=abono]" ).val();
			var medioPago = $( "#idMedioPago" ).val();
			
			var saldoPagar = montoDeuda - montoAbonos;
			
			if(abono <= 0){
				alert('El valor del abono debe ser mayor 0');
				return false;
			}
			
			if(montoAbonos == montoDeuda){
				alert('Esta deuda ya fue pagada');
				return false;
			}
			
			if(abono > saldoPagar){
				alert('El abono es mayor que el saldo de la deuda');
				return false;
			}
			
			if(medioPago == 0) {
				alert('Debe ingresar un medio de pago');
				return false;
			}
		
		$("#generarAbono").submit();	
			
	});	
	
	$( ".btn-aceptar, .btn-rechazar" ).click(function() {
		var fechasVencimiento = $(this).parent().parent().find(".fechaVencimiento").val();
		var today = new Date();
		
		var fechaDate = fechasVencimiento.replace("-","/");
		
		fechaDate = new Date(fechaDate);
		
		if(fechaDate < today){
			alert('La fecha debe ser mayor a la fecha actual');
			return false;
		}
		
		if($(this).hasClass("btn-aceptar")){
		
			if(fechasVencimiento == ""){
				alert('Ingrese fechas de vencimiento del lote');
				return false;
			}
		}
		
		if($(this).hasClass("btn-rechazar")){
			fechasVencimiento = '';
		}
			
		var href = $(this).attr("href");
		
		href += "?fechaVencimiento=" +fechasVencimiento;
		
		$(this).attr("href",href);

	});	
	
	//Pedido
	$(document).on('click', '.eliminarProductoPedido' ,function() {
		$(this).parent().parent().remove();
	});	
	
	//Venta
	$(document).on('click', '.eliminarProducto' ,function() {
		$(this).parent().parent().remove();
		
		//Recalcular total
		var sumatoria = 0;
		$( ".total" ).each(function( index, element ) {
			sumatoria += parseInt($(element).val());
			
		});
		
		$(".totalFinal").val(sumatoria);
		$(".montoPagar").val(sumatoria);
	});	
});