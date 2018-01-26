<?php

// Implementation note:
// --------------------
// If the argument to parse_binary isn't a valid binary value the
// function should raise an \InvalidArgumentException
// with a meaningful error message.

function parse_binary( $binary ) {
	if(! isValid($binary))
		throw new InvalidArgumentException();

	$out = 0;
	for ( $i = strlen( $binary ) - 1, $p = 0; $i >= 0; $i --, $p ++ ) {
		$out += $binary[ $i ] * pow( 2, $p );
	}

	return $out;
}

function isValid( $binary ) {
	return preg_match( "/^[0,1]+$/", $binary );
}
