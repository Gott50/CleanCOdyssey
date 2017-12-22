<?php

class Game {
	private $rolls = array();

	public function score() {
		$frames =[];
		$next = true;
		for ( $i = 0; $i < sizeof( $this->rolls ); $i ++ ) {
				$roll = $this->rolls[ $i ];
			if ( $next ) {
				array_push( $frames, $roll );
				if ( $frames[ sizeof( $frames ) - 1 ] == 10 ) {
					$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
					$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 2 ];
					$next = !$next;
				}
			}
			else{
				$frames[ sizeof( $frames ) - 1 ] += $roll;
				if ($frames[ sizeof( $frames ) - 1 ] == 10)
					$frames[ sizeof( $frames ) - 1 ] += $roll = $this->rolls[ $i + 1 ];
			}
			$next = !$next;
		}

		if(sizeof($frames) == 11)
			unset( $frames[10] );
		echo var_dump($frames);
		return  array_reduce($frames,function ($carry, $item){
			return $carry+$item;
		},0);
	}

	public function roll( $int ) {
		array_push( $this->rolls, $int );
	}
}