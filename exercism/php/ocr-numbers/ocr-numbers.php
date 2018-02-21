<?php

function recognize( $input ) {
	if ( ! is_valid( $input ) ) {
		throw new InvalidArgumentException();
	}

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
	if ( is_zero( $input ) ) {
		return "0";
	}
	if ( is_one( $input ) ) {
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
 *
 * @return bool
 */
function is_zero( $input ): bool {
	return $input == [
			" _ ",
			"| |",
			"|_|",
			"   ",
		];
}

/**
 * @param $input
 *
 *
 * @return bool
 */
function is_one( $input ): bool {
	return $input == [
			"   ",
			"  |",
			"  |",
			"   ",
		];
}
/**
 * @param $input
 *
 *
 * @return bool
 */
function is_two( $input ): bool {
	return $input == [
		" _ ",
		" _|",
		"|_ ",
		"   ",
	];
}
/**
 * @param $input
 *
 *
 * @return bool
 */
function is_three( $input ): bool {
	return $input == [
			" _ ",
			" _|",
			" _|",
			"   ",
		];
}
/**
 * @param $input
 *
 *
 * @return bool
 */
function is_four( $input ): bool {
	return $input == [
			"   ",
			"|_|",
			"  |",
			"   ",
		];
}
/**
 * @param $input
 *
 *
 * @return bool
 */
function is_five( $input ): bool {
	return $input == [
			" _ ",
			"|_ ",
			" _|",
			"   ",
		];
}

/**
 * @param $input
 *
 *
 * @return bool
 */
function is_seven( $input ): bool {
	return is_one( $input ) && $input[0][1] == "_";
}