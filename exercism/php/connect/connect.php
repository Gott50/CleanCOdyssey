<?php
function resultFor( $field ) {
	echo var_dump( $field );

	for ( $i = 0; $i < sizeof( $field );$i++ ) {
		if( $field[ $i ][0] != "."){
			if(strlen($field[ $i ]<= 1)) return winner($field[ $i ]);
		}
	}
}

/**
 * @param $color
 *
 * @return null|string
 */
function winner( $color ) {
	if ( $color == "X" ) {
		return "black";
	} else if ( $color == "O" ) {
		return "white";
	} else {
		return null;
	}
}