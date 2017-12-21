<?php
function toDecimal( $string ) {
	if ( ! isValid( $string ) ) {
		return 0;
	}

	$out = 0;
	for ( $i = 0; $i < strlen( $string ); $i ++ ) {
		$out += intval( $string[ $i ] ) * pow( 3, ( strlen( $string ) - $i - 1 ) );
	}

	return $out;
}

function isValid( $string ) {
	return preg_match( "/^[0-2]+$/", $string );
}