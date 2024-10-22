let anzahlGeschenke = 0;
let counterSpieler = 0;
let displaySpieler = "";
let ergebnisAuslosung = "";
const players = [];

function myFunction() {
	anzahlGeschenke = document.getElementById("anzahlGeschenke").value;
	document.getElementById("displayGeschenke").innerHTML = 
	  "Die Anzahl der Geschenke lautet: " + anzahlGeschenke;
}

function myFunction2() {
	const name = document.getElementById("spieler").value;
	players.push(name);
	counterSpieler++;
	displaySpieler += counterSpieler + ". SpielerIn: " + 
	players[counterSpieler - 1] + "<br>";
	document.getElementById("displaySpieler").innerHTML = displaySpieler;

}

function myFunction3() {
	const auslosung = shuffle2(counterSpieler, anzahlGeschenke);
	for (let i = 0; i < counterSpieler; i++) {
		ergebnisAuslosung += players[i] + " beschenkt " + 
		"<span class='spoiler'>";
		for (let j = 0; j < anzahlGeschenke; j++) {
			ergebnisAuslosung += players[auslosung[j][i]] + ", "
		}

		ergebnisAuslosung += "</span>" + "<br>";
	}
	document.getElementById("ergebnis").innerHTML = ergebnisAuslosung;
}

function shuffle1(participants) {
	const target = [];
	for (let i = 0; i < participants; i++) {
		target.push(i);
	}
	const target2 = target.slice();
	for (let i = 0; i < participants; i++) {
		let k = myRandom(participants - i);
		target2[i] = target[k];
		target[k] = target[participants - 1 - i];
	}
	return target2;
}

function myRandom(bound) {
	return Math.floor(Math.random() * bound);
}

function shuffle2(participants, presents) {
	let counter = 0;
	const result = [];
	while (counter < presents) {
		let nextShuffle = shuffle1(participants);
		result.push(nextShuffle);
		counter++;
		if (!isValidDistribution(result)) {
			result.pop();
			counter--;
		}
	}
	return result;
}

function isValidDistribution(distribution) { 
	let xlength = distribution.length;
	let ylength = distribution[0].length;
	if (xlength >= ylength) {
		return false;
	}
	for (let i = 0; i < xlength; i++) {
		for (let j = 0; j < ylength; j++) {
			if (distribution[i][j] === j) {
				return false;
			}
			for (let k = 0; k < i; k++) {
				if (distribution[k][j] == distribution[i][j]) {
					return false;
				}
			}
		}
	}
	return true;
}