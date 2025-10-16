const cartas = [
  { nombre: '1 de Espada', valor: 14 },
  { nombre: '1 de Basto', valor: 13 },
  { nombre: '7 de Espada', valor: 12 },
  { nombre: '7 de Oro', valor: 11 },
  { nombre: '3 de cualquier palo', valor: 10 },
  { nombre: '2 de cualquier palo', valor: 9 },
  { nombre: '1 de Copa / 1 de Oro', valor: 8 },
  { nombre: '12', valor: 7 },
  { nombre: '11', valor: 6 },
  { nombre: '10', valor: 5 },
  { nombre: '7 de Basto / Copa', valor: 4 },
  { nombre: '6', valor: 3 },
  { nombre: '5', valor: 2 },
  { nombre: '4', valor: 1 }
];

function obtenerCartaAleatoria() {
  const posiblesCartas = [
    '1 de Espada', '1 de Basto', '1 de Copa', '1 de Oro',
    '2 de Espada', '3 de Basto', '7 de Oro', '7 de Basto',
    '12 de Copa', '6 de Espada'
  ];
  const nombre = posiblesCartas[Math.floor(Math.random() * posiblesCartas.length)];
  const carta = cartas.find(c => nombre.startsWith(c.nombre.split(' ')[0]) && nombre.includes(c.nombre.split(' ')[2] || ""));
  return { nombre, valor: carta ? carta.valor : 0 };
}

function jugarCarta() {
  const jugador = obtenerCartaAleatoria();
  const bot = obtenerCartaAleatoria();

  let resultado = `Vos: ${jugador.nombre} (${jugador.valor})<br>`;
  resultado += `Bot: ${bot.nombre} (${bot.valor})<br>`;

  if (jugador.valor > bot.valor) {
    resultado += "<strong>¡Ganaste la mano!</strong>";
  } else if (jugador.valor < bot.valor) {
    resultado += "<strong>Perdiste la mano.</strong>";
  } else {
    resultado += "<strong>¡Empate!</strong>";
  }

  document.getElementById('resultado').innerHTML = resultado;
}


