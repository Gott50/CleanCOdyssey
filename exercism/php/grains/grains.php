<?php

function square( $index ) {
	$out = 2 ** ( $index - 1 );
	if ( ! is_float( $out ) ) {
		return $out . "";
	}
}