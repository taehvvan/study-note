<?php
	$a = 3;
	$b = 5;

	$c = $a + $b;
	$c++;

	$c = $c + $a;
	$d = $a + $c * $b;

	echo "\$d : $d";
	echo "<br>";

	$a = 10;
	$b = $a % 3;
	$b--;

	$c = $a - $b;
	$c = $c - 5;

	echo "\$c : $c";
?>