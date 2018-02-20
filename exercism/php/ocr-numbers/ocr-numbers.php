<?php

function recognize( $input ) {
	if ( ! is_valid( $input ) ) {
		throw new InvalidArgumentException();
	}

	$out = "";
	for ( $i = 0; $i < strlen( $input[0] ); $i += 3 ) {
		$out .= recognize_digit( $input, $i );
	}

	return $out;
}

/**
 * @param $input
 *
 * @param $index
 *
 * @return string
 */
function recognize_digit( $input, $index ): string {
	if ( is_zero( $input, $index ) ) {
		return "0";
	}

	if ( is_one( $input, $index ) ) {
		return "1";
	}

	return "?";
}

/**
 * @param $input
 *
 * @return bool
 */
function is_valid( $input ): bool {
	return are_rows_valid( $input ) && are_columns_valid( $input );
}

/**
 * @param $input
 *
 * @return bool
 */
function are_columns_valid( $input ): bool {
	$length = function ( $acc, $row ) {
		return $acc && strlen( $row ) % 3 == 0;
	};

	return array_reduce( $input, $length, true );
}

/**
 * @param $input
 *
 * @return bool
 */
function are_rows_valid( $input ): bool {
	return sizeof( $input ) == 4;
}

/**
 * @param $input
 *
 * @param $index
 *
 * @return bool
 */
function is_zero( $input, $index ): bool {
	return is_seven( $input, $index ) && $input[2][ 1 + $index ] == "_"
	       && $input[1][ 0 + $index ] == "|" && $input[2][ 0 + $index ] == "|";
}

/**
 * @param $input
 *
 * @param $index
 *
 * @return bool
 */
function is_one( $input, $index ): bool {
	return $input[1][ 2 + $index ] == "|" && $input[2][ 2 + $index ] == "|";
}

/**
 * @param $input
 *
 * @param $index
 *
 * @return bool
 */
function is_seven( $input, $index ): bool {
	return is_one( $input, $index ) && $input[0][ 1 + $index ] == "_";
}