<?php

function recognize( $input ) {
	if ( ! is_valid( $input ) ) {
		throw new InvalidArgumentException();
	}

	$out = "";
	for ( $i = 0; $i < sizeof( $input ); $i += 4 ) {
		$out .= "," . recognize_row( array_slice( $input, $i, 4 ) );
	}

	return substr( $out, 1 );
}

/**
 * @param $input
 *
 * @return string
 */
function recognize_row( $input ): string {
	$out = "";
	for ( $i = 0; $i < strlen( $input[0] ); $i += 3 ) {
		$sub = array_map( function ( $a ) use ( $i ) {
			return substr( $a, $i, 3 );
		}, $input );
		$out .= recognize_digit( $sub, $i );
	}

	return $out;
}

/**
 * @param $input
 *
 * @return string
 */
function recognize_digit( $input ): string {
	$digits = [
		[
			" _ ",
			"| |",
			"|_|",
			"   ",
		],
		[
			"   ",
			"  |",
			"  |",
			"   ",
		],
		[
			" _ ",
			" _|",
			"|_ ",
			"   ",
		],
		[
			" _ ",
			" _|",
			" _|",
			"   ",
		],
		[
			"   ",
			"|_|",
			"  |",
			"   ",
		],
		[
			" _ ",
			"|_ ",
			" _|",
			"   ",
		],
		[
			" _ ",
			"|_ ",
			"|_|",
			"   ",
		],

		[
			" _ ",
			"  |",
			"  |",
			"   ",
		],
		[
			" _ ",
			"|_|",
			"|_|",
			"   ",
		],
		[
			" _ ",
			"|_|",
			" _|",
			"   ",
		]
	];

	for ( $i = 0; $i <= 9; $i ++ ) {
		if ( $input == $digits[ $i ] ) {
			return $i . "";
		}
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
	return sizeof( $input ) % 4 == 0;
}