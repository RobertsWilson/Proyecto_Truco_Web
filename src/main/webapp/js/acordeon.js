// Acordeón para mostrar/ocultar contenido
  document.querySelectorAll('.acordeon-titulo').forEach(boton => {
    boton.addEventListener('click', () => {
      const contenido = boton.nextElementSibling;
      const activo = contenido.style.display === 'block';

      // Cerrar todos
      document.querySelectorAll('.acordeon-contenido').forEach(c => c.style.display = 'none');

      // Abrir solo si no estaba activo
      if (!activo) {
        contenido.style.display = 'block';
      }
    });
  });
