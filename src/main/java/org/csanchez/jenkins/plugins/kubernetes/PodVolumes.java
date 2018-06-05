package org.csanchez.jenkins.plugins.kubernetes;

import java.util.List;

import io.fabric8.kubernetes.api.model.VolumeMount;

@Deprecated
public class PodVolumes {

    @Deprecated
    public static abstract class PodVolume extends org.csanchez.jenkins.plugins.kubernetes.volumes.PodVolume {
        private static final long serialVersionUID = -4563542579530618024L;
    }

    @Deprecated
    public static class EmptyDirVolume extends org.csanchez.jenkins.plugins.kubernetes.volumes.EmptyDirVolume {

        private static final long serialVersionUID = -5525423285873662546L;

        public EmptyDirVolume(String mountPath, Boolean memory) {
            super(mountPath, memory);
        }

        protected Object readResolve() {
            return new org.csanchez.jenkins.plugins.kubernetes.volumes.EmptyDirVolume(this.getMountPath(),
                    this.getMemory());
        }
    }

    @Deprecated
    public static class SecretVolume extends org.csanchez.jenkins.plugins.kubernetes.volumes.SecretVolume {

        private static final long serialVersionUID = -3962363635990517065L;

        public SecretVolume(String mountPath, String secretName) {
            super(mountPath, secretName);
        }

        protected Object readResolve() {
            return new org.csanchez.jenkins.plugins.kubernetes.volumes.SecretVolume(this.getMountPath(),
                    this.getSecretName());
        }
    }

    @Deprecated
    public static class HostPathVolume extends org.csanchez.jenkins.plugins.kubernetes.volumes.HostPathVolume {

    	private static final long serialVersionUID = 2260587971424224380L;

        public HostPathVolume(String hostPath, String mountPath) {
            super(hostPath, mountPath);
        }

        protected Object readResolve() {
            return new org.csanchez.jenkins.plugins.kubernetes.volumes.HostPathVolume(this.getHostPath(),
                    this.getMountPath());
        }
    }

    @Deprecated
    public static class NfsVolume extends org.csanchez.jenkins.plugins.kubernetes.volumes.NfsVolume {
        private static final long serialVersionUID = -2183260230914601398L;

        public NfsVolume(String serverAddress, String serverPath, Boolean readOnly, String mountPath) {
            super(serverAddress, serverPath, readOnly, mountPath);
        }

        protected Object readResolve() {
            return new org.csanchez.jenkins.plugins.kubernetes.volumes.NfsVolume(this.getServerAddress(),
                    this.getServerPath(), this.getReadOnly(), this.getMountPath());
        }
    }

    /**
     * @deprecated Use {@link PodVolume#volumeMountExists(String, List)} instead
     */
    public static boolean volumeMountExists(String path, List<VolumeMount> existingMounts) {
        return PodVolume.volumeMountExists(path, existingMounts);
    }

    /**
     * @deprecated Use {@link PodVolume#podVolumeExists(String,List)} instead
     */
    public static boolean podVolumeExists(String path, List<PodVolume> existingVolumes) {
        for (PodVolume podVolume : existingVolumes) {
            if (podVolume.getMountPath().equals(path)) {
                return true;
            }
        }
        return false;
    }
}
