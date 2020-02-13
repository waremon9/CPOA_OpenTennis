<?php
class Matchs{

    private $_noCourt;

    public function __construct($noCourt)
    {
        $this->_noCourt = $noCourt;
    }

    public function noCourt()
    {
        return $this->_noCourt;
    }

  
}