<?php

function square( $index ) {
	$out = 2 ** ( $index - 1 );
	if ( ! is_float( $out ) ) {
		return $out . "";
	}

	$int_size = PHP_INT_SIZE * 8 - 2;
	$out      = 2 ** ( $int_size ) . "";
	for ( $i = $int_size; $i < $index - 1; $i ++ ) {
		$out = double( $out );
	}

	return $out;
}

function double( $number ) {
	$out = "";
	for ( $i = 0; $i < strlen( $number ); $i ++ ) {
		$d = $number[ $i ] * 2;
		if ( $d >= 10 && strlen( $out ) >= 0 ) {
			$out[ strlen( $out ) - 1 ] = $out[ strlen( $out ) - 1 ] + 1;
		}
		$out .= $d % 10;
	}

	return $out;
}