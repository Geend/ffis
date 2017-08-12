<?php
defined( '_JEXEC' ) or die( 'Restricted access' );
require_once( dirname(__FILE__).'/helper.php' );

$msg = "";
$currentOp = modOpIndicatorHelper::getOperation($params);



if($currentOp == "op")
	$msg = "Ja";
else if ($currentOp == "nop")
	$msg = "Nein";
else if ($currentOp == "ni")
	$msg = "Unbekannt";

require(JModuleHelper::getLayoutPath('mod_opindicator'));
?>
