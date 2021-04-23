package org.ruqinhu.reactor.netty;

public class Release {

    public static void main(String[] arg) {
        String url = "http://git.yueguanjia.com/leo.li/ygj-agreement.git";
        String localPath = "D:\\test\\";
        String branchName = "sso ";
        String username = "";
        String password = "";
        try {
            //0.命令拼接
            String[] result = url.split("/");
            String projectName = result[result.length - 1].split("\\.")[0];
            System.out.println(projectName);
            //1. 克隆代码
            String cloneCommand = "cmd /c d: && cd d:\\test && git clone -b " + branchName + " " + result[0] + "//" + username + ":" + password + "@"
                    + result[2] + "/" + result[3] + "/" + result[4];
            System.out.println(cloneCommand);
//            Process cloneProcess = Runtime.getRuntime().exec(cloneCommand);
//            cloneProcess.waitFor();
            System.out.println("clone success");
            //2.执行mvn打包 并等待命令执行完成
            //String real = "cmd /c d: && cd d:\\project && git clone -b master http://peizhouyu:123456.@git.mrpei.com/peizhouyu/HelloTest.git && cd d:\\project\\HelloTest && mvn clean package -Dmaven.test.skip=true";
            String packageCommand = "cmd /c d: && cd d:\\test\\" + projectName + " && mvn clean package -Dmaven.test.skip=true";
            System.out.println(packageCommand);
//            Process process = Runtime.getRuntime().exec(packageCommand);
//            process.waitFor();
            System.out.println("package finish");
            //3.上传文件服务 target文件夹 后缀 jar文件

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
