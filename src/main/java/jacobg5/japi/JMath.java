package jacobg5.japi;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class JMath {
    public static float Lerp(float a, float b, float f) {
        return a + (b - a) * f;
    }
    public static double Lerp(double a, double b, double f) {
        return a + (b - a) * f;
    }

    public static Vec3d Lerp(Vec3d a, Vec3d b, double f) {
        return new Vec3d(Lerp(a.getX(), b.getX(), f), Lerp(a.getY(), b.getY(), f), Lerp(a.getZ(), b.getZ(), f));
    }


    public static void lookAtTarget(Entity entity, Entity target) {
        entity.setPitch(getTargetPitch(new Vec3d(entity.getX(), entity.getEyeY(), entity.getZ()), new Vec3d(target.getX(), target.getY() + (target.getHeight() / 2), target.getZ())));
        entity.setYaw(getTargetYaw(entity.getPos(), target.getPos()));
    }

    public static Float getTargetPitch(Vec3d origin, Vec3d target) {
        double d = target.getX() - origin.getX();
        double e = target.getY() - origin.getY();
        double f = target.getZ() - origin.getZ();
        double g = Math.sqrt(d * d + f * f);
        return Float.valueOf((float)(-(MathHelper.atan2(e, g) * 57.2957763671875)));
    }

    public static Float getTargetYaw(Vec3d origin, Vec3d target) {
        double d = target.getX() - origin.getX();
        double e = target.getZ() - origin.getZ();
        return Float.valueOf((float)(MathHelper.atan2(e, d) * 57.2957763671875) - 90.0f);
    }
}
