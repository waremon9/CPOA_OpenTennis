<?php
class Ticket_client{

    private $_type;
    private $_id_client;
    private $_id_match;
    private $_horaire;
    private $_court;
    private $_zone;
    private $_place;

    public function __construct($type, $id_client, $id_match, $horaire, $court, $zone, $place)
    {
        $this->_type = $type;
        $this->_id_client = $id_client;
        $this->_id_match = $id_match;
        $this->_horaire = $horaire;
        $this->_court = $court;
        $this->_zone = $zone;
        $this->_place = $place;
    }

    public function type()
    {
        return $this->_type;
    }
    public function id_client()
    {
        return $this->_id_client;
    }
    public function id_match()
    {
        return $this->_id_match;
    }
    public function horaire()
    {
        return $this->_horaire;
    }
    public function court()
    {
        return $this->_court;
    }
    public function zone()
    {
        return $this->_zone;
    }
    public function place()
    {
        return $this->_place;
    }
}