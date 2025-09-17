package jacobg5.japi.containers;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestStorage;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.stream.Stream;

public class JPointOfInterest {
    public final Identifier identifier;
    public final PointOfInterestType poi;
    public final RegistryKey<PointOfInterestType> key;

    public JPointOfInterest(PointOfInterestType poi, Identifier identifier) {
        this.identifier = identifier;
        this.poi = poi;
        this.key = RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, identifier);
    }

    public Stream<PointOfInterest> range(ServerWorld world, BlockPos pos, int radius, PointOfInterestStorage.OccupationStatus status, boolean square) {
        PointOfInterestStorage storage = world.getPointOfInterestStorage();

        if (square) return storage.getInSquare(poiType -> poiType.matchesKey(key), pos, radius, status);
        return storage.getInCircle(poiType -> poiType.matchesKey(key), pos, radius, status);
    }

    public Stream<PointOfInterest> range(ServerWorld world, BlockPos pos, int radius, boolean square) {
        return range(world,pos, radius, PointOfInterestStorage.OccupationStatus.ANY, square);
    }

    public Stream<PointOfInterest> range(ServerWorld world, BlockPos pos, int radius, PointOfInterestStorage.OccupationStatus status) {
        return range(world,pos, radius, status, false);
    }

    public Stream<PointOfInterest> range(ServerWorld world, BlockPos pos, int radius) {
        return range(world,pos, radius, PointOfInterestStorage.OccupationStatus.ANY, false);
    }

    public PointOfInterest closest(ServerWorld world, BlockPos pos, int radius, PointOfInterestStorage.OccupationStatus status) {
        PointOfInterest stored = null;
        double distance = Float.MAX_VALUE;

        for (PointOfInterest poi : range(world, pos, radius, status, false).toList()) {
            double d = pos.getSquaredDistance(poi.getPos().toCenterPos());
            if (d < distance)
            {
                distance = d;
                stored = poi;
            }
        }
        return stored;
    }

    public PointOfInterest closest(ServerWorld world, BlockPos pos, int radius) {
        return closest(world, pos, radius, PointOfInterestStorage.OccupationStatus.ANY);
    }
}
