<?php

function accumulate( array $input, callable $accumulator ) {
	for ( $i = 0; $i < sizeof( $input ); $i ++ ) {
		$input[ $i ] = $accumulator( $input[ $i ] );
	}

	return $input;
}
