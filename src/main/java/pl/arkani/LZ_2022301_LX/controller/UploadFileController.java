//package pl.arkani.LZ_2022301_LX.controller;
//
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import pl.arkani.LZ_2022301_LX.service.StorageService;
//
//
//@Controller
//@RequestMapping(value = "fileupload")
//public class UploadFileController {
//
//	private final StorageService storageService;
//
//	@Autowired
//	public UploadFileController(StorageService storageService) {
//		this.storageService = storageService;
//	}
//
//	@GetMapping("/listuploadedfiles")
//	public String listUploadedFiles(Model model) throws IOException {
//
//		model.addAttribute("files", storageService.loadAll().map(
//				path -> MvcUriComponentsBuilder.fromMethodName(UploadFileController.class,
//						"serveFile", path.getFileName().toString()).build().toUri().toString())
//				.collect(Collectors.toList()));
//
//		return "fileupload/fileupload";
//	}
//
//	@GetMapping("/files/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//		Resource file = storageService.loadAsResource(filename);
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	}
//
//	@PostMapping("/listuploadedfiles")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file,
//			RedirectAttributes redirectAttributes) {
//
//		storageService.store(file);
//		redirectAttributes.addFlashAttribute("message",
//				"You successfully uploaded " + file.getOriginalFilename() + "!");
//
//		return "redirect:/listuploadedfiles";
//	}
//
////	@ExceptionHandler(StorageFileNotFoundException.class)
////	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
////		return ResponseEntity.notFound().build();
////	}
//
//}