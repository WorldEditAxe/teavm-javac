package processing.platform.teavm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.interop.Export;
import org.teavm.interop.Import;
import org.teavm.jso.JSBody;
import org.teavm.jso.typedarrays.Int8Array;

public final class FileAssetBridge {
  private static final Map<Integer, AsyncCallback<Object>> pendingCallbacks = new HashMap<>();
  private static int nextRequestId;

  private FileAssetBridge() {
  }

  public static String loadFont(String path, byte[] data) throws IOException {
    int resultId = loadFont(
        Int8Array.copyFromJavaArray(path.getBytes(StandardCharsets.UTF_8)),
        Int8Array.copyFromJavaArray(data));
    return stringResult(resultId, "Font load failed");
  }

  public static String loadImage(String path, byte[] data) throws IOException {
    int resultId = loadImage(
        Int8Array.copyFromJavaArray(path.getBytes(StandardCharsets.UTF_8)),
        Int8Array.copyFromJavaArray(data));
    return stringResult(resultId, "Image load failed");
  }

  private static String stringResult(int resultId, String fallbackMessage) throws IOException {
    try {
      var bytes = operationBytesToJava(resultId);
      if (bytes.length == 0) {
        throw new IOException(fallbackMessage);
      }
      if (bytes[0] == 0) {
        return new String(bytes, 1, bytes.length - 1, StandardCharsets.UTF_8);
      }
      var message = bytes.length > 1
          ? new String(bytes, 1, bytes.length - 1, StandardCharsets.UTF_8)
          : fallbackMessage;
      throw new IOException(message);
    } finally {
      releaseOperationBytes(resultId);
    }
  }

  @Async
  private static native int loadFont(Int8Array pathBytes, Int8Array data);

  @SuppressWarnings("unchecked")
  private static void loadFont(Int8Array pathBytes, Int8Array data, AsyncCallback<Integer> callback) {
    int requestId = ++nextRequestId;
    pendingCallbacks.put(requestId, (AsyncCallback<Object>) (AsyncCallback<?>) callback);
    loadFontAsyncStart(requestId, pathBytes, data);
  }

  @Import(module = "teavmFile", name = "loadFontAsyncStart")
  @JSBody(params = { "requestId", "pathBytes", "data" }, script =
    "globalThis.teavmFile.loadFontAsyncStart(requestId, pathBytes, data);")
  private static native void loadFontAsyncStart(int requestId, Int8Array pathBytes, Int8Array data);

  @Async
  private static native int loadImage(Int8Array pathBytes, Int8Array data);

  @SuppressWarnings("unchecked")
  private static void loadImage(Int8Array pathBytes, Int8Array data, AsyncCallback<Integer> callback) {
    int requestId = ++nextRequestId;
    pendingCallbacks.put(requestId, (AsyncCallback<Object>) (AsyncCallback<?>) callback);
    loadImageAsyncStart(requestId, pathBytes, data);
  }

  @Import(module = "teavmFile", name = "loadImageAsyncStart")
  @JSBody(params = { "requestId", "pathBytes", "data" }, script =
    "globalThis.teavmFile.loadImageAsyncStart(requestId, pathBytes, data);")
  private static native void loadImageAsyncStart(int requestId, Int8Array pathBytes, Int8Array data);

  @Export(name = "teavm_asset_load_complete")
  public static void completeLoadImage(int requestId, int resultId) {
    var callback = pendingCallbacks.remove(requestId);
    if (callback != null) {
      callback.complete(resultId);
    }
  }

  private static byte[] operationBytesToJava(int resultId) {
    var length = operationByteLength(resultId);
    var bytes = new byte[length];
    for (int i = 0; i < length; i++) {
      bytes[i] = operationByteAt(resultId, i);
    }
    return Arrays.copyOf(bytes, bytes.length);
  }

  @Import(module = "teavmFile", name = "operationByteLength")
  @JSBody(params = { "resultId" }, script = "return globalThis.teavmFile.operationByteLength(resultId);")
  private static native int operationByteLength(int resultId);

  @Import(module = "teavmFile", name = "operationByteAt")
  @JSBody(params = { "resultId", "index" }, script = "return globalThis.teavmFile.operationByteAt(resultId, index);")
  private static native byte operationByteAt(int resultId, int index);

  @Import(module = "teavmFile", name = "releaseOperationBytes")
  @JSBody(params = { "resultId" }, script = "globalThis.teavmFile.releaseOperationBytes(resultId);")
  private static native void releaseOperationBytes(int resultId);
}
