## Exchange of messagges  
  
**Login message**  
*login  < User > name < User >*  
  
**login answer**  
*"logged/not accepted"*  
  
**play message**   (single player or multiplayer)  
*play  < Type > type < Type >*  
  
**play answer**  
*yes  < Id > IdMatch < Id >*  
  
**request move(server)**  
*moveAvailable*  
*  < Type 1 >
	*  < Pos >
		* _pos_
	*  < XY >
		* _xypos_  
*  < Type 2 >
	*  < Dice >
		*  < Number >
			* _num_
		*  < Colour >
			* _col_  
*  < Type 3 >
	*  < ToolCard >
		*  < Name >
			* _name_
		*  < Descr >
			* _descr_ 

**answer message type of move**  
*move  < TypeM > move < TypeM >*  
  
**disconnection message**  
*disconnected  < Name > user < Name >*  
  
**view Map**  
*view*  
*  < Dice >
	*  < Number >
		* _num_
	*  < Colour >
		* _col_
	*  < X >
		* _x_
	*  < Y >
		* _y_  
*  < Restr1 >
	*  < Colour >
		* _col_
	*  < X >
		* _x_
	*  < Y >
		* _y_  
*  < Restr2 >
	*  < Number >
		* _num_
	*  < X >
		* _x_
	*  < Y >
		* _y_  
