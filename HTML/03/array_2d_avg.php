<?php
	$score = array(array(88, 98, 96, 77, 63),
					array(86, 77, 66, 86, 93),
					array(74, 83, 95, 86, 97)
				);
	$sum = 0;


	for($i = 0; $i < 3; $i++) 
	{
		for($j = 0; $j < 5; $j++) 
		{
			echo "\$score[$i][$j] = ".$score[$i][$j]."<br>";
			$sum = $sum + $score[$i][$j];
		}
		$avg = $sum / 5;
		echo "합계 : $sum, 평균 : $avg <br>";
		$sum = 0;
		echo "<br>";
	}
?>