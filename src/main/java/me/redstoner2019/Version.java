package me.redstoner2019;

public class Version {
    private int major;
    private int minor;
    private int patch;
    private int alpha;
    private int beta;
    public Version(String version){
        Version v = Version.fromString(version);
        this.major = (v.getMajor());
        this.minor = (v.getMinor());
        this.patch = (v.getPatch());
        this.alpha = (v.getAlpha());
        this.beta = (v.getBeta());
        System.out.println(toString());
    }
    public Version(int major, int minor, int patch){
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.alpha = 0;
        this.beta = 0;
    }
    public void setAlpha(int alpha){
        this.alpha = alpha;
        this.beta = 0;
    }
    public void setBeta(int beta){
        this.alpha = 0;
        this.beta = beta;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getBeta() {
        return beta;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public void setPatch(int patch) {
        this.patch = patch;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }
    public boolean isAlpha(){
        return alpha != 0;
    }
    public boolean isBeta(){
        return beta != 0;
    }

    @Override
    public String toString() {
        String adding = "";
        if(alpha > 0){
            adding = "-alpha."+alpha;
        }
        if(beta > 0){
            adding = "-beta."+beta;
        }
        return "v" + major + "." + minor + "." + patch + adding;
    }

    /**
     *
     * @param v Value to check if the current object is older than the value
     * @return
     */
    public boolean isOlder(Version v){
        if(isEqual(v)) return false;

        int majorVal = v.getMajor() - getMajor();
        int minorVal = v.getMinor() - getMinor();
        int patchVal = v.getPatch() - getPatch();
        int alphaVal = v.getAlpha() - getAlpha();
        int betaVal = v.getBeta() - getBeta();

        if(majorVal < 0){
            return false;
        } else if(majorVal == 0){
            if(minorVal < 0){
                return false;
            } else if(minorVal == 0){
                if(patchVal < 0){
                    return false;
                } else if(patchVal == 0){
                    /**
                     * Checking Alpha versioning
                     */
                    if(isAlpha() && v.isAlpha()){
                        return alphaVal >= 0;
                    } else if(isBeta() && v.isAlpha()){
                        return false;
                    } else if(isAlpha() && v.isBeta()){
                        return true;
                    }  else if(isBeta() && v.isBeta()){
                        return betaVal >= 0;
                    }else {
                        return false;
                    }

                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    public boolean isNewer(Version v){
        if(isEqual(v)) return false;
        return !isOlder(v);
    }
    public boolean isEqual(Version v){
        return v.toString().equals(toString());
    }

    public static Version fromString(String versionString){
        if(versionString.startsWith("v")){
            versionString = versionString.substring(1);
        } else {
            return null;
        }
        Version version = new Version(1,0,0);
        String[] splitMajor = versionString.split("-");
        try{
            if(splitMajor.length == 1){
                String[] splitMinor = splitMajor[0].split("\\.");
                if(splitMinor.length == 3){
                    version.setMajor(Integer.parseInt(splitMinor[0]));
                    version.setMinor(Integer.parseInt(splitMinor[1]));
                    version.setPatch(Integer.parseInt(splitMinor[2]));
                } else {
                    return null;
                }
            } else if(splitMajor.length == 2){
                String[] splitMinor = splitMajor[0].split("\\.");
                if(splitMinor.length == 3){
                    version.setMajor(Integer.parseInt(splitMinor[0]));
                    version.setMinor(Integer.parseInt(splitMinor[1]));
                    version.setPatch(Integer.parseInt(splitMinor[2]));
                } else {
                    return null;
                }
                splitMinor = splitMajor[1].split("\\.");
                if(splitMinor.length == 2){
                    if(splitMinor[0].equals("alpha")){
                        version.setAlpha(Integer.parseInt(splitMinor[1]));
                    } else if(splitMinor[0].equals("beta")){
                        version.setBeta(Integer.parseInt(splitMinor[1]));
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return version;
    }
}