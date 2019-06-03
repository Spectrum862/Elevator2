
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RandomNumber 
{
	Human human;
	int timeh = 0 ,timem = 0;
	int maxfloor;
	
	public static void main(String[] args)
	{
		
		
		int timeh = 0 ,timem = 0;
		int maxfloor , foodfloor = 0 ;
	
		
		Scanner mytimeh = new Scanner(System.in);
		Scanner mytimem = new Scanner(System.in);
		Scanner myfloor = new Scanner(System.in);
		Scanner myfoodfloor = new Scanner(System.in);
		
		
		//�Ѻ��� �������
		System.out.print("timeh : ");
		timeh = mytimeh.nextInt(); 
		
		//�Ѻ��� �ҷ�
		System.out.print("timem : ");
		timem = mytimem.nextInt(); 
		
		//�Ѻ��� �ӹǹ����٧�ش
		System.out.print("number of max floor :");
		maxfloor = myfloor.nextInt();
		
		// �Ѻ��� �����ҹ�����
		System.out.print("number of Food floor :");
		foodfloor = myfoodfloor.nextInt();
		
		
		// �Өӹǹ���ѧ���
		RandomNumber a = new RandomNumber();
		
		a.format(maxfloor, timeh ,timem,foodfloor);
		
	}
	
	
	public double format(int maxfloor ,int timenowh ,int timenowm,int foodfloor)
	{
		int lambda = 0;
		int number = 0;
		int floornow = 0;
		int all = 0;
		int minuteday = 0;
		
		RandomNumber show = new RandomNumber();
		
		//�ŧ���Ҩҡ������� ��йҷ��� ����Ţ  1-1440
		minuteday = (timenowh *60 ) + timenowm ;
		
		
		LinkedList<Human> list = new LinkedList<>();
		
		
		//===========================================================================
		
		// ����� 7.00 �֧  7.30 ���  ��觵͹��� ��餹 ���Ҩҡ���1 ��ҹ�� ������ѧ�ء���
		if(minuteday >= 420 &&  minuteday <= 450) {
			for (int floorgo =1 ; floorgo <= maxfloor ; floorgo++)
			{
			lambda = 3;
			number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					// �ç����ͷ���˹���� �����Ҩҡ��� 1 ��ҹ��
					floornow = 1 ;
					
					if (floorgo == floornow) {
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//show.show(all, floornow, direction, floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				}
			}
		}
		
		//����� 7.31 ���֧ 11 ��� �ء���С�Ш�µ��价ء�������ѹ
		else if (minuteday >= 451 &&  minuteday <= 660){
			for (int floorgo =1 ; floorgo <= maxfloor ; floorgo++)
			{
			lambda = 2;
			number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					
					if (floorgo == floornow) {
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				}
			}
			}
		
		// ����� 11.01 ���֧ 13.00 ��� �ء���С�Ш�µ��价ء�ѹ����ѹ ¡��鹪����ҹ�����
		else if(minuteday >= 661 &&  minuteday <= 780)
		{
			for (int floorgo =1 ; floorgo <= maxfloor ; floorgo++)
			{
				
				if(floorgo == foodfloor) //��Ҫ�鹷����ѧǹ�ٻ���� �繪����ҹ����� ������������Ң�� ��9
				{
					lambda = 6;
					number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					if (floorgo == floornow) 
					{
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				}
				}
				else  //�ء��� �����������2
				{
					lambda = 2;
					number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					if (floorgo == floornow) 
					{
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				}
				}
			}
		}
		// ����� 13.01 ���֧ 18.30 ��� 
		else if(minuteday >= 781 &&  minuteday <= 1110) {
			for (int floorgo =1 ; floorgo <= maxfloor ; floorgo++)
			{
			lambda = 2;
			number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					
					if (floorgo == floornow) {
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				
				}
			}
		}
		// ����� 18.31 ���֧ 19.00 ���
		else if(minuteday >= 1111 &&  minuteday <= 1140) {
			for (int floorgo =1 ; floorgo <= maxfloor ; floorgo++)
			{
				
				if(floorgo == 1)
				{
					lambda = 6;
					number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					if (floorgo == floornow) 
					{
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				}
				}
				else
				{
					lambda = 1;
					number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					if (floorgo == floornow) 
					{
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				}
				}
			}
			
		}
		// ����� 19.01 ���֧ 6.59 �ͧ�ѹ����
		else if(minuteday >= 1141 &&  minuteday <= 1440 || minuteday >= 0 &&  minuteday <= 419) {
			for (int floorgo =1 ; floorgo <= maxfloor ; floorgo++)
			{
			lambda = 1;
			number = getPoisson(lambda);
			
				for(int i = 1;i <= number ;i++)
				{
					floornow = getrandom(maxfloor);
					
					if (floorgo == floornow) {
						continue;
					}
					all = all +1;
					String direction = direction(floorgo,floornow);
					//System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
					human = new Human(direction,floorgo);
				    if(direction.equals("up")) Building.floor[floornow-1].addHumanUp(human);
				    else Building.floor[floornow-1].addHumanDown(human);
				
				}
			}
		}
		//System.out.printf("\n number all  %6d " ,all);
		return 0;
	}
	
	
	public int show(int all,int floornow,String direction,int floorgo)
	{
		System.out.printf("\n %4d number   floornow    %6d : %7s : floorgo %6d" ,all,floornow,direction,floorgo);
		return 0;	
	}
	
	
	public String direction(int floorgo ,int floornow) {
		int CheckDirection = 0 ;
		String direction = null ;
		
		CheckDirection = floorgo - floornow;
		if(CheckDirection < 0) {
			direction = "down";
		}
		else {
			direction = "up";
		}
		return direction;
	}
	
	public static int getPoisson(double lambda)
	{
	  double L = Math.exp(-lambda);
	  double p = 1.0;
	  int k = 0;

	  do {
	    k++;
	    p *= Math.random();
	  } while (p > L);

	  return k - 1;
	}
	
	public int getrandom(int maxfloor)
	{
		int numberramdom =1 ; 
		numberramdom = new Random().nextInt(maxfloor)+ 1;
		return numberramdom;
	}                                        
	
}

