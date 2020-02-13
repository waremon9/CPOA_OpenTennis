<?php
class Ticket_type{

	private $_type;
	private $_price;
	private $_ticket_number;
	private $_promo_code;

	public function __construct($type, $price, $ticket_number, $promo_code)
    {
        $this->_type = $type;
        $this->_price = $price;
        $this->_ticket_number = $ticket_number;
        $this->_promo_code = $promo_code;
    }

    public function type()
    {
        return $this->_type;
    }
    public function price()
    {
        return $this->_price;
    }
    public function ticket_number()
    {
        return $this->_ticket_number;
    }
    public function promo_code()
    {
        return $this->_promo_code;
    }


}