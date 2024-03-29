//package gx.common.helper.html.js;
//
//import javax.script.*;
//import java.io.FileReader;
//
//public class JsEngineTestor {
//	public static void main(String[] args) throws Exception {
//		ScriptEngineManager manager = new ScriptEngineManager();
//		ScriptEngine engine = manager.getEngineByName("javascript");
//
//		String jsFileName = "C:\\test.js";
//		// 读取js文件
//		FileReader reader = new FileReader(jsFileName);
//		// 执行指定脚本
//		engine.eval(reader);
//		if(engine instanceof Invocable) {
//			Invocable invoke = (Invocable)engine;
//
//
//			Double c = (Double)invoke.invokeFunction("merge", 2, 3);
//			System.out.println("c = " + c);
//
//			//invoke.invokeFunction("setCookie", "test", "test");
//
//			invoke.invokeFunction("testAlert");
//
//
//		}
//		reader.close();
//	}
//
//}
//
