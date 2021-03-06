package net.ryanhecht.ParksQuest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

//Everything WIP, of course, and still messy as I work on stuff
public class NPC {
	Plugin plugin = Main.getPlugin();
	protected ArmorStand stand;
	Integer i;
	int j=0;
	public NPC(Location l) {
		
		stand = l.getWorld().spawn(l, ArmorStand.class);
		stand.setArms(true);
		stand.setBasePlate(false);
		stand.setMarker(true);
		i=Integer.valueOf(1);
	}
	public NPC() {
		
	}
	protected void walk(final Location d, final double v) {
        new BukkitRunnable() {
        	
            @Override
            public void run() {
                if((int)stand.getLocation().getX()==(int)d.getX() && (int)stand.getLocation().getY()==(int)d.getY() && (int)stand.getLocation().getZ() ==(int)d.getZ()) {
                	stand.setVelocity(new Vector(0,0,0));
                	i = new Integer(0);
                	walkAnimate();
                	this.cancel();
                }
                else {
                	stand.setVelocity(new Vector(v,0,0));
                	walkAnimate();
                }
            }
           
        }.runTaskTimer(plugin, 1, 1);
	}
	private void walkAnimate() {
		j++;
		double w=stand.getVelocity().getX() * (Math.PI/4);
		if(i.equals(1)) {
           	stand.setLeftArmPose(new EulerAngle(stand.getLeftArmPose().getX()-w, stand.getLeftArmPose().getY(), stand.getLeftArmPose().getZ()));
        	stand.setRightArmPose(new EulerAngle(stand.getRightArmPose().getX()+w, stand.getRightArmPose().getY(), stand.getRightArmPose().getZ()));
        	stand.setLeftLegPose(new EulerAngle(stand.getLeftLegPose().getX()+w, stand.getLeftLegPose().getY(), stand.getLeftLegPose().getZ()));
        	stand.setRightLegPose(new EulerAngle(stand.getRightLegPose().getX()-w, stand.getRightLegPose().getY(), stand.getRightLegPose().getZ()));
        	
		}
		if(i.equals(-1)) {
           	stand.setLeftArmPose(new EulerAngle(stand.getLeftArmPose().getX()+w, stand.getLeftArmPose().getY(), stand.getLeftArmPose().getZ()));
        	stand.setRightArmPose(new EulerAngle(stand.getRightArmPose().getX()-w, stand.getRightArmPose().getY(), stand.getRightArmPose().getZ()));
        	stand.setLeftLegPose(new EulerAngle(stand.getLeftLegPose().getX()-w, stand.getLeftLegPose().getY(), stand.getLeftLegPose().getZ()));
        	stand.setRightLegPose(new EulerAngle(stand.getRightLegPose().getX()+w, stand.getRightLegPose().getY(), stand.getRightLegPose().getZ()));
		}
		if(i.equals(0)) {
        	stand.setLeftArmPose(new EulerAngle(0,0,0));
        	stand.setRightArmPose(new EulerAngle(0,0,0));
        	stand.setRightLegPose(new EulerAngle(0,0,0));
        	stand.setLeftLegPose(new EulerAngle(0,0,0));
        	Bukkit.getServer().getLogger().info(""+j);
		}
		if(Math.abs(stand.getLeftArmPose().getX() - (Math.PI/4)) <= .1) {
			i = new Integer(1);
		}
		if(Math.abs(stand.getLeftArmPose().getX() + (Math.PI/4)) <= .1) {
			i = new Integer(-1);	
		}
	}
}
